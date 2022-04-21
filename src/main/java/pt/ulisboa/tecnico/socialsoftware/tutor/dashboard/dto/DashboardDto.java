package pt.ulisboa.tecnico.socialsoftware.tutor.dashboard.dto;

import pt.ulisboa.tecnico.socialsoftware.tutor.dashboard.domain.Dashboard;

import pt.ulisboa.tecnico.socialsoftware.tutor.utils.DateHandler;

public class DashboardDto {
    private Integer id;
    private String lastCheckDifficultQuestions;

    public DashboardDto() {}

    public DashboardDto(Dashboard dashboard) {
        id = dashboard.getId();
        lastCheckDifficultQuestions = DateHandler.toISOString(dashboard.getLastCheckDifficultQuestions());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastCheckDifficultQuestions() {
        return lastCheckDifficultQuestions;
    }

    public void setLastCheckDifficultQuestions(String lastCheckDifficultQuestions) {
        this.lastCheckDifficultQuestions = lastCheckDifficultQuestions;
    }

    @Override
    public String toString() {
        return "DashboardDto{" +
                "id=" + id +
                ", lastCheckDifficultQuestions" + lastCheckDifficultQuestions +
                "}";
    }
}