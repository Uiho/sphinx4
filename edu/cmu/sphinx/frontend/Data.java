/**
 * [[[copyright]]]
 */

package edu.cmu.sphinx.frontend;

import java.io.Serializable;

/**
 * Implements the base class for all Data objects that go between
 * DataSources. Subclass of Data contain will contain the actual
 * data. A Data object can also contain a Signal.
 * 
 * <p>Data can be event signals or just data to be processed, and they go
 * through the processors in the front-end pipeline. Data can be
 * audio, preemphasized audio data, cepstra, etc.. Signals
 * can be used to indicate events like beginning/end of audio
 * segment, data dropped, quality changed, etc..
 *
 * <p><b>IMPORTANT:</b>
 * Subclass of Data that are assumed to contain actual data by default.
 * Therefore, calling <code>Data.getSignal()</code> will return
 * <code>Signal.CONTENT</code>. The programmer must override this behavior
 * with the constructor.
 *
 * @see Signal
 */
public class Data implements Serializable {

    private Signal signal = Signal.CONTENT;

    /**
     * Constructs a Data object with the given Signal.
     *
     * @param signal the Signal of this Data object
     */
    protected Data(Signal signal) {
        this.signal = signal;
    }


    /**
     * Returns the Signal of this Data. Returns null if no Signal present.
     *
     * @return the Signal of this Data, or null if no Signal present
     */
    public Signal getSignal() {
        return signal;
    }


    /**
     * Returns true if this Data has content in it. Effectively it
     * checks whether <code>getSignal()</code> returns
     * <code>Signal.CONTENT</code>.
     *
     * Subclass of Data that are assumed to contain actual data by default.
     * Therefore, this will return <code>Signal.CONTENT</code> unless the
     * Data subclass constructor calls the <code>Data(Signal)</code>
     * constructor.
     *
     * @return true if it has content, false otherwise
     */
    public boolean hasContent() {
        return (getSignal().equals(Signal.CONTENT));
    }


    /**
     * Returns true if this Data has a SEGMENT_END Signal in it.
     *
     * @return true if it has a SEGMENT_END Signal, false otherwise
     */
    public boolean hasSegmentEndSignal() {
        if (getSignal() == null) {
            return false;
        } else {
            return (getSignal().equals(Signal.SEGMENT_END));
        }
    }
}
