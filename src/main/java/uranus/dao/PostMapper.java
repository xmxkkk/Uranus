package uranus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import uranus.model.Post;

@Component
@Mapper
public interface PostMapper {
	
	@Insert("insert into post (title,content,summary,add_time,modify_time,keywords,description,url,origin_url,index_id,status,error_times) values "
			+ "(#{title},#{content},#{summary},#{add_time},#{modify_time},#{keywords},#{description},#{url},#{origin_url},#{index_id},#{status},#{error_times})")
	int insert(Post post);
	
	@Delete("delete from post where id=#{id}")
	int delete(@Param("id")int id);
	
	@Update("update post set title=#{title},content=#{content},summary=#{summary},add_time=#{add_time}"
			+ ",modify_time=#{modify_time},keywords=#{keywords},description=#{description},url=#{url},origin_url=#{origin_url},index_id=#{index_id},status=#{status},error_times=#{error_times} where id=#{id}")
	int update(Post post);
	
	@Select("select * from post where origin_url=#{origin_url}")
	Post findOriginUrl(@Param("origin_url")String origin_url);
	
	@Select("select * from post where status=#{status} and error_times<#{error_times}")
	List<Post> findStatusErrorTimes(@Param("status")int status,@Param("error_times")int error_times);
	
	@Select("select * from post where (content is null or content = '') and error_times<#{error_times}")
	List<Post> findErrorTimes(@Param("error_times")int error_times);
}
