package com.search.job.indeed.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("skills")
public class Skill implements Serializable {

        private static final long serialVersionUID = 1L;

        private String index;
        @Id
        private String id;

        private String description;


}
