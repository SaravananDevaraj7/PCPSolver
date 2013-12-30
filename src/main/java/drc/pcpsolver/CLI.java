/*
PCPSolver. Java solver for the Post Correspondence Problem.
Copyright 2013 David Catteeuw

This file is part of PCPSolver.

PCPSolver is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or (at your
option) any later version.

PCPSolver is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License
along with PCPSolver.  If not, see <http://www.gnu.org/licenses/>.
*/

package drc.pcpsolver;

import java.util.Iterator;
import java.util.LinkedList;
import drc.jsearch.*;
import drc.pcpsolver.pcp.*;

public class CLI
{
    static final String TOP_BOTTOM_SEPARATOR = "/";
    static final int maxDepth = 10;

    final Pcp pcp;
    final Solver solver;

    public CLI (String[] dominoStrings) {
	pcp = new Pcp(getDominoes(dominoStrings, TOP_BOTTOM_SEPARATOR));
	solver = new Solver();
    }

    Domino stringToDomino (String dominoString, String separator) {
	String[] parts = dominoString.split(separator);
	return new Domino(parts[0], parts[1]);
    }
    
    Domino[] getDominoes (String[] dominoStrings, String separator) {
	Domino[] dominoes = new Domino[dominoStrings.length];
	for (int i=0; i < dominoStrings.length; i++) {
	    dominoes[i] = stringToDomino(dominoStrings[i], separator);
	}
	return dominoes;
    }

    String solutionToString (Node solution) {
	LinkedList<Node> path = solution.pathTo();
	String top="", bottom="";
	Iterator<Node> it = path.iterator();
	it.next();
	PcpAction pcpAction = (PcpAction) it.next().action;
	top = top.concat(pcpAction.domino.top);
	bottom = bottom.concat(pcpAction.domino.bottom);
	while (it.hasNext()) {
	    top = top.concat(" | ");
	    bottom = bottom.concat(" | ");
	    pcpAction = (PcpAction) it.next().action;
	    top = top.concat(pcpAction.domino.top);
	    bottom = bottom.concat(pcpAction.domino.bottom);
	}
	return top + "\n" + bottom + "\n";
    }

    void run () {
	Solver.ReasonNoSolution reason = solver.findReasonNoSolution(pcp);
	if (reason != null) {
	    System.out.println("PCP instance has no match." + reason.explanation());
	} else {
	    Node solution = solver.findMatch(pcp, maxDepth);
	    if (solution != null) {
		System.out.println("PCP instance has a match: ");
		System.out.println(solutionToString(solution));
	    } else {
		System.out.println("PCP instance has no match of length less " +
				   "than or equal to " + maxDepth);
	    }
	}
    }

    public static void main(String[] args) {
	new CLI(args).run();
    }
}
