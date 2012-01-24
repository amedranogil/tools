//============================================================================
//
// Copyright (C) 2002-2006  David Schneider, Lars K�dderitzsch, Fabrice Bellingard
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//
//============================================================================

package net.sf.eclipsecs.ui.stats.export.internal;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.eclipsecs.ui.stats.data.Stats;
import net.sf.eclipsecs.ui.stats.export.IStatsExporter;
import net.sf.eclipsecs.ui.stats.export.StatsExporterException;

/**
 * Abstract exporter.
 * 
 * @author Fabrice BELLINGARD
 */
public abstract class AbstractStatsExporter implements IStatsExporter {
    private String mMainFontName;

    private int mMainFontSize;

    /**
     * {@inheritDoc}
     * 
     * @see net.sf.eclipsecs.stats.export.IStatsExporter#initialize(java.util.Map)
     */
    public void initialize(Map props) throws StatsExporterException {
        if (props == null) {
            // use the defaults
            mMainFontName = DEFAULT_MAIN_FONT_NAME;
            mMainFontSize = DEFAULT_MAIN_FONT_SIZE.intValue();
        }
        else {
            // Font name
            Object font = props.get(PROPS_MAIN_FONT_NAME);
            if (font instanceof String) {
                mMainFontName = (String) font;
            }
            else {
                mMainFontName = DEFAULT_MAIN_FONT_NAME;
            }
            // Font size
            Object size = props.get(PROPS_MAIN_FONT_SIZE);
            if (size instanceof Integer) {
                mMainFontSize = ((Integer) size).intValue();
            }
            else {
                mMainFontSize = DEFAULT_MAIN_FONT_SIZE.intValue();
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see net.sf.eclipsecs.stats.export.IStatsExporter#generate(net.sf.eclipsecs.stats.data.Stats,
     *      java.util.List, java.io.File)
     */
    public void generate(Stats stats, List details, File outputFile) throws StatsExporterException {
        // checks if the values provided are correct
        if (stats == null) {
            throw new StatsExporterException("No statistics to export...");
        }
        if (outputFile == null) {
            throw new StatsExporterException("The output file is null...");
        }

        // and if everything's fine, go!
        doGenerate(stats, details, outputFile);
    }

    /**
     * Generates the document containing the exported stats.
     * 
     * @param stats the Checkstyle statistics we want to export
     * @param details the list of markers detailed for one of a Checkstyle error
     *            category
     * @param outputFile the file into which the stats will be exported
     * @throws StatsExporterException if an error occurs while generating the
     *             document
     */
    protected abstract void doGenerate(Stats stats, List details, File outputFile)
        throws StatsExporterException;

    /**
     * @return Returns the mMainFontName.
     */
    protected String getMainFontName() {
        return mMainFontName;
    }

    /**
     * @return Returns the mMainFontSize.
     */
    protected int getMainFontSize() {
        return mMainFontSize;
    }
}