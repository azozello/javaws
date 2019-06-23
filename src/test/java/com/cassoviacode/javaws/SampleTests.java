package com.cassoviacode.javaws;

import com.cassoviacode.javaws.model.Building;
import com.cassoviacode.javaws.payload.BuildingPayload;
import com.cassoviacode.javaws.repository.BuildingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles("test")
public class SampleTests extends BaseTestCase {

    @Autowired
    private BuildingRepository buildingRepository;

    //TODO This test must pass!
    @Test
    public void testRetrieveBuildings() throws Exception {
        List<BuildingPayload> buildings = getPayloadRecords(
                "/topic/city",
                BuildingPayload.class,
                "/app/get-all-buildings",
                true);
        Assert.assertEquals(1, 1);
        Assert.assertEquals(3, buildings.size());
        Assert.assertEquals("Building1", buildings.get(0).getName());
        Assert.assertEquals("Building5", buildings.get(buildings.size() - 1).getName());
    }

    @Override
    public void initDatabase() {
        List<Building> listBuildings = new ArrayList<>();
        listBuildings.add(new Building("Building1", 150, true));
        listBuildings.add(new Building("Building2", 362, false));
        listBuildings.add(new Building("Building3", 315, true));
        listBuildings.add(new Building("Building4", 400, false));
        listBuildings.add(new Building("Building5", 120, true));
        listBuildings.add(new Building("Building6", 100, false));
        this.buildingRepository.saveAll(listBuildings);
    }
}
