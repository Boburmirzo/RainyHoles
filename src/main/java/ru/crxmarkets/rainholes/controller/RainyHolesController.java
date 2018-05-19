package ru.crxmarkets.rainholes.controller;

import ru.crxmarkets.rainholes.domain.Hole;
import ru.crxmarkets.rainholes.domain.WaterVolume;
import ru.crxmarkets.rainholes.service.HoleSurfaceVolumeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Handles Hole input and calculation.
 * In addition it builds beautiful google chart model for
 * given surface profile.
 *
 * @author Bobur Umurzokov
 */
@Named
@RequestScoped
public class RainyHolesController {

    @Inject
    private HoleSurfaceVolumeService surfaceWaterCalc;

    private String surfaceProfile;
    private Hole hole;

    private WaterVolume waterVolume;

    public String getSurfaceProfile() {
        return surfaceProfile;
    }

    public void setSurfaceProfile(String surfaceProfile) {
        this.surfaceProfile = surfaceProfile;
    }

    public void validateSurfaceProfile(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String surfaceProfile = (String) value;
        try {
            hole = new Hole(surfaceProfile);
        } catch (IllegalArgumentException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }

    public Hole getSurface() {
        return hole;
    }

    public void setSurface(Hole hole) {
        this.hole = hole;
    }

    public void calculate() {
        setWaterVolumeResult(surfaceWaterCalc.calculateWaterVolume(getSurface()));
    }

    public void setWaterVolumeResult(WaterVolume waterVolumeResult) {
        this.waterVolume = waterVolumeResult;
    }

    public WaterVolume getWaterVolumeResult() {
        return waterVolume;
    }
}
