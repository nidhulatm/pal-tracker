package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
   private String portNum;
    private String memoryLimit;
    private String instanceInd;
    private String instanceAddrs;



    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memLimit,
                         @Value("${cf.instance.index:NOT SET}") String instanceIndex,
                         @Value("${cf.instance.addr:NOT SET}") String instanceAddr ) {
        portNum = port;
        memoryLimit = memLimit;
        instanceInd = instanceIndex;
        instanceAddrs = instanceAddr;
    }


    @GetMapping("/env")
    public Map<String,String> getEnv(){
        Map<String, String> env = new HashMap<>();

        env.put("PORT", portNum);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", instanceInd);
        env.put("CF_INSTANCE_ADDR", instanceAddrs);

        return env;

    }

}
