package me.ryanalexander.BetterScreenshare.Variables;

import me.ryanalexander.BetterScreenshare.Variables.Reasons.KillAura;
import me.ryanalexander.BetterScreenshare.Variables.Reasons.Other;
import me.ryanalexander.BetterScreenshare.Variables.Reasons.Reach;
import me.ryanalexander.BetterScreenshare.Variables.Reasons.Speed;

public enum ScreenshareReason {
    KILLAURA(new KillAura()),
    KA(new KillAura()),
    AIMBOT(new KillAura()),
    COMBAT(new KillAura()),

    REACH(new Reach()),

    SPEED(new Speed()),
    BHOP(new Speed()),
    BUNNYHOP(new Speed()),

    OTHER(new Other());

    Reason reason;
    ScreenshareReason(Reason reason){
        this.reason=reason;
    }

    @Override
    public String toString() {
        return this.reason.getTranslation();
    }
}
