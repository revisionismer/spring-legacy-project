package com.nexchal.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.nexchal.sample.mappers.TimeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class TimeServiceImpl implements TimeService {
	
	private final TimeMapper timeMapper;

	@Override
	public int insert1(String str) {
		log.info("---------------- 트랜잭션 테스트 insert 1 ------------------");
		System.out.println("트랜잭션 상태 : " + TransactionSynchronizationManager.isActualTransactionActive());
		return timeMapper.insert1(str);
	}

	@Override
	public int insert2(String str) {
		log.info("---------------- 트랜잭션 테스트 insert 2 ------------------");
		System.out.println("트랜잭션 상태 : " + TransactionSynchronizationManager.isActualTransactionActive());
		return timeMapper.insert2(str);
	}

	@Override
	public void insertBoth(String str) {
	    log.info("---------------- 트랜잭션 테스트 insertBoth ------------------");
	    System.out.println("트랜잭션 상태 : " + TransactionSynchronizationManager.isActualTransactionActive());

	    timeMapper.insert1(str);
	    timeMapper.insert2(str); // 예외 발생 시 전체 롤백
	}
}
