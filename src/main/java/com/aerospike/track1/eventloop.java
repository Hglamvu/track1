package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.async.EventLoops;
import com.aerospike.client.async.EventPolicy;
import com.aerospike.client.async.NioEventLoop;
import com.aerospike.client.async.NioEventLoops;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

public class eventloop {
    public static void main (String [] args){
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.eventLoops = createEventLoops(4);
        //kết nối 
        AerospikeClient client = new AerospikeClient("localhost", 3000);

        Key key = new Key("test", "demo","key1");
        Bin bin = new Bin("name","vhlam" );

        //ghi du lieu bat dong bo bang vong lap su kien
        WritePolicy writePolicy = new WritePolicy();
        client.put(writePolicy, key,bin);

        Record record = client.get(null,key);
        //in ra du lieu
        if(record!=null){
            System.out.println("Record found: " + record.getValue("name"));
        }else{
            System.out.println("Record not found");
        }
        client.close();
    }
    private static EventLoops createEventLoops(int numLoops) {
        EventPolicy eventPolicy = new EventPolicy();
        return new NioEventLoops(eventPolicy, numLoops);
    }
}
