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

package drc.pcpsolver.pcp;

import drc.jsearch.*;

public class PcpAction implements ActionInterface
{
    public final Domino domino;
    
    PcpAction (Domino domino) {
	this.domino = domino;
    }

    @Override
    public String toString () {
	return new String("<PcpAction: " + domino.toString() + ">");
    }
}
