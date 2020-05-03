package com.peishujuan.springcloud.jpa.tests.dao;

import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 不需要加注解
 * UserRepository 继承了 CrudRepository 就有了增删改查的功能
 * UserRepository 继承了 PagingAndSortingRepository 就有了分页和排序的方法
 * UserRepository 继承了 JpaRepository
 *
 * PagingAndSortingRepository 继承了 CrudRepository
 * JpaRepository 继承了 PagingAndSortingRepository
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>, JpaSpecificationExecutor<UserEntity> {

    //Jpa下 自定义方法 以findBy开始的方法
    List<UserEntity> findByUsername(String username);

    //模糊查询
    List<UserEntity> findByUsernameLike(String username);

    //排序查询
    List<UserEntity> findByUsernameLikeOrderByIdDesc(String username);

    //分页,
    Page<UserEntity> findByUsername(String username, Pageable pageable);

    //排序,
    List<UserEntity> findByUsername(String username, Sort sort);

    //自定义方法-删除
    @Transactional
    int deleteByUsername(String username);
    @Transactional
    int deleteByUsernameAndSex(String username,Integer sex);
    @Transactional
    int deleteBySexIsNull();

    //注解的方法@query
    //单条件查询 ?1,?2 是方法的参数顺序
    @Query("select u from UserEntity u where u.username=?1 and u.password=?2")
    List<UserEntity> queryByUsernameAndPassword(String username,String password);
    //使用原生sql
    @Query(value = "select * from tb_user where username=?1 and password=?2",nativeQuery = true)
    List<UserEntity> selectByUsernameAndPassword(String username,String password);
    //使用@Param参数注解--推荐使用
    @Query("select u from UserEntity u where u.username=:uname and u.password=:pwd")
    List<UserEntity> queryByUsernameAndPasswordParam(@Param("uname") String username, @Param("pwd")String password);
    //使用@Param参数注解 查询并分页
    @Query("select u from UserEntity u where u.username=:uname and u.password=:pwd")
    List<UserEntity> queryByUsernameAndPasswordParam(@Param("uname") String username, @Param("pwd")String password,Pageable pageable);

    //通过query注解 更新表字段,在修改时要加上注解 @Modifying,@Transactional
    @Modifying
    @Transactional
    @Query("update UserEntity u set u.password=:pwd where id=:id")
    int updatePasswordById(@Param("id") Integer id, @Param("pwd") String password);

}
