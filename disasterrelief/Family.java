package disasterrelief;

class Family {
    String name;
    String location;
    int totalPoints;

    int foodNeed;
    int waterNeed;
    int medicalNeed;

    public Family(String name, String location, int totalPoints,
                  int foodNeed, int waterNeed, int medicalNeed) {
        this.name = name;
        this.location = location;
        this.totalPoints = totalPoints;
        this.foodNeed = foodNeed;
        this.waterNeed = waterNeed;
        this.medicalNeed = medicalNeed;
    }

    @Override
    public String toString() {
        return name + " (" + location + ") - Points: " + totalPoints +
               " | Needs [Food: " + foodNeed + ", Water: " + waterNeed +
               ", Medical: " + medicalNeed + "]";
    }
}

