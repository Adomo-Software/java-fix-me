package JFM_4;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws FloorUnderContructionException {
        Floor firstFloor = new AvailableFloor(1);
        Floor secondFloor = new UnavailableFloor(2);
        Floor thirdFloor = new AvailableFloor(3);

        Map<Integer, Floor> map = Map.of(
                firstFloor.getNumber(), firstFloor,
                secondFloor.getNumber(), secondFloor,
                thirdFloor.getNumber(), thirdFloor
        );
        Floor floor = map.get(3);
        System.out.println(floor.getFloorNumber());
    }

}

interface Floor {
    Integer getFloorNumber() throws FloorUnderContructionException;
    Integer getNumber();
}

class UnavailableFloor implements Floor {
    private final Integer floorNumber;

    UnavailableFloor(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public Integer getFloorNumber() throws FloorUnderContructionException {
        throw new FloorUnderContructionException("Floor " + floorNumber + " is under contruction");
    }

    @Override
    public Integer getNumber() {
        return this.floorNumber;
    }
}

class AvailableFloor implements Floor {
    private final Integer floorNumber;

    AvailableFloor(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public Integer getFloorNumber() {
        return floorNumber;
    }

    @Override
    public Integer getNumber() {
        return this.floorNumber;
    }
}

