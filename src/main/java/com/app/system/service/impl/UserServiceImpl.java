package com.app.system.service.impl;

import java.util.*;

import com.app.common.utils.*;
import com.app.system.service.DeptService;
import com.app.system.vo.UserVO;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.common.domain.Tree;
import com.app.system.dao.DeptDao;
import com.app.system.dao.UserDao;
import com.app.system.dao.UserRoleDao;
import com.app.system.domain.DeptDO;
import com.app.system.domain.UserDO;
import com.app.system.domain.UserRoleDO;
import com.app.system.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    DeptDao deptMapper;

    @Autowired
    DeptService deptService;

    @Value("${app.uploadPath}")
    private String uploadPath;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
//    @Cacheable(value = "user",key = "#id")
    public UserDO get(Long id) {
        List<Long> roleIds = userRoleMapper.listRoleId(id);
        UserDO user = userMapper.get(id);
        user.setDeptName(deptMapper.get(user.getDeptId()).getName());
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        String deptId = map.get("deptId").toString();
        if (StringUtils.isNotBlank(deptId)) {
            Long deptIdl = Long.valueOf(deptId);
            List<Long> childIds = deptService.listChildrenIds(deptIdl);
            childIds.add(deptIdl);
            map.put("deptId", null);
            map.put("deptIds",childIds);
        }
        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    @Override
    public int save(UserDO user) {
        int count = userMapper.save(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(UserDO user) {
        int r = userMapper.update(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return r;
    }

    //    @CacheEvict(value = "user")
    @Override
    public int remove(Long userId) {
        userRoleMapper.removeByUserId(userId);
        return userMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
        if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
            if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
                return userMapper.update(userDO);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(UserVO userVO) throws Exception {
        UserDO userDO = get(userVO.getUserDO().getUserId());
        if ("admin".equals(userDO.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
        return userMapper.update(userDO);


    }

    @Transactional
    @Override
    public int batchremove(Long[] userIds) {
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>(16));
        Long[] pDepts = deptMapper.listParentDept();
        Long[] uDepts = userMapper.listAllDept();
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (DeptDO dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
                continue;
            }
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<UserDO> users = userMapper.list(new HashMap<String, Object>(16));
        for (UserDO user : users) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(user.getUserId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int updatePersonal(UserDO userDO) {
        return userMapper.update(userDO);
    }

}
