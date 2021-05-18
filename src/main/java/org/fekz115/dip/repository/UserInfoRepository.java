package org.fekz115.dip.repository;

import org.fekz115.dip.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {}
