package lab1;

class KBTUOpenContestant {
    public enum TshirtSizes {
        XS, S, M, L, XL, XXL
    }
    private String name, surname;
    TshirtSizes tshirtSize;

    public KBTUOpenContestant(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.tshirtSize = TshirtSizes.XL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public TshirtSizes getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(TshirtSizes tshirtSize) {
        this.tshirtSize = tshirtSize;
    }
}

class KBTUOpenTeam {
    public enum ParticipationLocation {
        ONSITE, ONLINE
    }
    private ParticipationLocation location;
    private boolean allWomen;
    private boolean allSchool;

    public static final int MAX_MEMBERS = 3;
    private int currentMemberCount = 0;
    private static int totalRegisteredTeams = 0;

    private final int teamId;
    private String teamName;
    private final KBTUOpenContestant[] contestants = new KBTUOpenContestant[3];

    public KBTUOpenTeam(String teamName) {
        this(teamName, ParticipationLocation.ONLINE);
    }
    public KBTUOpenTeam(String teamName, ParticipationLocation location) {
        this.teamName = teamName;
        this.teamId = ++totalRegisteredTeams;
        this.location = location;
        this.allWomen = false;
        this.allSchool = false;
    }

    public void registerMember(KBTUOpenContestant contestant) {
        if (currentMemberCount < MAX_MEMBERS) {
            contestants[currentMemberCount] = contestant;
            currentMemberCount++;
            System.out.println("Registered " + contestant.getName() + " to team " + this.teamName);
        } else {
            throw new RuntimeException("Team is full!");
        }
    }

    public boolean isAllWomen() {
        return allWomen;
    }

    public void setAllWomen(boolean allWomen) {
        this.allWomen = allWomen;
    }

    public boolean isAllSchool() {
        return allSchool;
    }

    public void setAllSchool(boolean allSchool) {
        this.allSchool = allSchool;
    }

    public int getCurrentMemberCount() {
        return currentMemberCount;
    }

    public void setCurrentMemberCount(int currentMemberCount) {
        this.currentMemberCount = currentMemberCount;
    }

    public static int getTotalRegisteredTeams() {
        return totalRegisteredTeams;
    }

    public static void setTotalRegisteredTeams(int totalRegisteredTeams) {
        KBTUOpenTeam.totalRegisteredTeams = totalRegisteredTeams;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public KBTUOpenContestant[] getContestants() {
        return contestants;
    }

    public ParticipationLocation getLocation() {
        return location;
    }

    public void setLocation(ParticipationLocation location) {
        this.location = location;
    }
}

public class Problem2 {
    static void solution() {
        KBTUOpenTeam team = new KBTUOpenTeam("Team 1", KBTUOpenTeam.ParticipationLocation.ONSITE);
        team.registerMember(new KBTUOpenContestant("Alice", "Kanich"));
        team.registerMember(new KBTUOpenContestant("Bob", "Kanich"));
        team.registerMember(new KBTUOpenContestant("Carol", "Kanich"));
        System.out.println(team.getCurrentMemberCount());
        System.out.println(KBTUOpenTeam.getTotalRegisteredTeams());
        System.out.println(team.getTeamId());
    }
}
