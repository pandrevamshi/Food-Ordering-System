package org.vamshi.cassandra;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.Cluster;
import org.vamshi.dto.OrderObject;

@AllArgsConstructor
@NoArgsConstructor
public class CassandraDao {
    Cluster cluster;
    String query;

    public void writeToCassandra(OrderObject object) {

    }

}
