package org.incsoft.kakfaTest;


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.Cluster;

public class EpcEsPartitioner extends RoundRobinPartitioner implements Partitioner{


	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		try {
			if(key instanceof String) {
				return Integer.parseInt((String)key) % cluster.availablePartitionsForTopic(topic).size();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return super.partition(topic, key, keyBytes, value, valueBytes, cluster);
	}



}
