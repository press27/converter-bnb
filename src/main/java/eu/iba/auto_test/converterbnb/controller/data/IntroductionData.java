package eu.iba.auto_test.converterbnb.controller.data;

public class IntroductionData {

    private Long rkkId;

    private Long introductionId;

    public IntroductionData() {
    }

    public Long getRkkId() {
        return rkkId;
    }

    public IntroductionData setRkkId(Long rkkId) {
        this.rkkId = rkkId;
        return this;
    }

    public Long getIntroductionId() {
        return introductionId;
    }

    public IntroductionData setIntroductionId(Long introductionId) {
        this.introductionId = introductionId;
        return this;
    }
}
