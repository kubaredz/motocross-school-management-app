package project.end.mas.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.end.mas.exceptions.CantJoinTournamentException;
import project.end.mas.exceptions.NoTournamentException;
import project.end.mas.enums.Message;
import project.end.mas.models.Attendance;
import project.end.mas.models.Tournament;
import project.end.mas.models.Motocross;

import project.end.mas.models.Rider;
import project.end.mas.repositories.RiderRepository;
import project.end.mas.services.TournamentService;
import project.end.mas.services.MotocrossService;
import project.end.mas.services.AttendanceService;
import project.end.mas.services.RiderService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class JoinTournamentController {

    private final MotocrossService motocrossService;
    private final AttendanceService attendanceService;
    private final TournamentService tournamentService;
    private final RiderService riderService;
    private final RiderRepository riderRepository;

    @GetMapping("/")
    public String getHome(Model model) {
        Optional<Rider> loggedRider = riderRepository.findById(1L);
        model.addAttribute("rider", loggedRider.get());
        return "home.html";
    }

    /**
     * <p> method showing all open competitions</p>
     * @return view tournament-list.html
     */
    @GetMapping("/tournaments")
    public String getCompetitions(Model model) {

        if (!tournamentService.checkOpen())
            model.addAttribute("msgTournaments", Message.TOURNAMENTS_NONE.getMessage());

        Iterable<Tournament> openCompetitions = tournamentService.showOpenCompetitions();
        Optional<Rider> loggedRider = riderRepository.findById(1L);

        model.addAttribute("rider", loggedRider.get());
        model.addAttribute("tournaments", openCompetitions);

        return "tournament-list.html";
    }

    /**
     * <p> method showing participants in a chosen competition
     * and option to join it with a specific horse</p>
     * @return view tournament-details.html
     */
    @GetMapping("/tournament/{id}")
    public String getCompetitionDetails(Model model, @PathVariable long id) throws NoTournamentException {

        Tournament tournament = tournamentService.findCompetitionById(id)
                .orElseThrow(() -> new NoTournamentException("given tournament id doesn't exists"));
        List<Attendance> attendances = attendanceService.showParticipants(id);
        List<Motocross> motorcycle = motocrossService.showActiveHorses(tournament);

        if (motorcycle.isEmpty())
            model.addAttribute("msgMotocross", Message.MOTOCROSS_NONE.getMessage());

        Optional<Rider> loggedRider = riderRepository.findById(1L);
        model.addAttribute("rider", loggedRider.get());

        model.addAttribute("tournament", tournament);
        model.addAttribute("attendances", attendances);
        model.addAttribute("motorcycle", motorcycle);

        return "tournament-details.html";
    }

    /**
     * <p> method to add new participation
     * @return view tournament-details.html
     */
    @PostMapping("/tournament/{id}")
    public String joinCompetition(@PathVariable long id,
                                  @RequestParam(value = "newMotocross", required = false) Long newMotocross,
                                  RedirectAttributes redirectAttributes)
            throws CantJoinTournamentException {

        redirectAttributes.addFlashAttribute("msg", Message.FAILED_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-error");

        Tournament tournament = tournamentService.findCompetitionById(id).orElse(null);
        Rider loggedRider = riderRepository.findById(1L).orElse(null);

        if (!riderService.checkStars(tournament, loggedRider)) {
            return "redirect:/tournament/{id}";
        }

        attendanceService.joinCompetition(id, newMotocross);

        redirectAttributes.addFlashAttribute("msg", Message.SUCCESS_JOIN.getMessage());
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/tournament/{id}";
    }

}
