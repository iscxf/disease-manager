package com.app.common.service;

import org.springframework.stereotype.Service;

import com.app.common.domain.LogDO;
import com.app.common.domain.PageDO;
import com.app.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
