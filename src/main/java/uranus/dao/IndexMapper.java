package uranus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import uranus.model.Index;

@Component
@Mapper
public interface IndexMapper {
	
	@Insert("insert into `index` (url,fetch_time,interval_time,charset,all_link_pattern,link_pattern,theme) values "
			+ "(#{url},#{fetch_time},#{interval_time},#{charset},#{all_link_pattern},#{link_pattern},#{theme})")
	int insert();
	
	@Update("update `index` set fetch_time=#{fetch_time},interval_time=#{interval_time},charset=#{charset},all_link_pattern=#{all_link_pattern}"
			+ ",link_pattern=#{link_pattern},theme=#{theme} where url=#{url}")
	int update(Index index);
	
	@Select("select * from `index` where DATE_ADD(fetch_time,INTERVAL interval_time SECOND)<now()")
	List<Index> findTime();
	
	@Select("select * from `index` where DATE_ADD(fetch_time,INTERVAL interval_time SECOND)<now() and status=#{status}")
	List<Index> findTimeStatus(@Param("status")int status);
	
	@Select("select * from `index`")
	List<Index> find();
	
	@Select("select * from `index` where status=#{status}")
	List<Index> findStatus(@Param("status")int status);
	
	@Select("select * from `index` where url=#{url}")
	Index findUrl(@Param("url")String url);
	
	@Select("select * from `index` where id=#{id}")
	Index findId(@Param("id")int id);
	
}
