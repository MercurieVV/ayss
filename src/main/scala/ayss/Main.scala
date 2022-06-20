package com.github.mercurievv
package ayss

import javax.sound.midi.*
import java.util.*
import cats.implicits.*
import cats.effect.{ExitCode, IO, IOApp, Resource, Sync}

object Main extends IOApp {
  /*
  while (true) {

    // Exit the program when sequencer has stopped playing.
    if (!sequencer.isRunning()) {
      sequencer.close()
      System.exit(1)
    }
  }
   */
  import javax.sound.midi.MidiSystem
  import javax.sound.midi.Sequencer
  override def run(args: scala.List[String]): IO[ExitCode] = {
    runF[IO].flatMap(_ => IO.pure(ExitCode.Success))
  }

  def runF[F[_]: Sync] = {
    val res = Resource.make(Sync[F].delay {
      val sequencer = MidiSystem.getSequencer
      sequencer.open()
      sequencer
    })(a =>
      Sync[F].delay {
        a.close()
      }
    )

    res.use(sequencer => {
      for {
        sequence: Sequence <- Sync[F].delay(new Sequence(Sequence.PPQ, 4))
        track: Track       <- Sync[F].delay { sequence.createTrack() }
        _ = {
          for {
            note <- fs2.Stream.apply(1, 2, 3, 4)
            midiMessage = new ShortMessage(???, ???, note, ???)
//            midiMessage = new ShortMessage(command, channel, note, velocity)
            event = new MidiEvent(midiMessage, 1)
            _ <- fs2.Stream.eval(Sync[F].delay { track.add(event) })
          } yield ()
        }.compile.toList
      } yield ()

      Sync[F].delay {
        val sequence = new Sequence(Sequence.PPQ, 4)
        val track    = sequence.createTrack()

        sequencer.setSequence(sequence)
        // Specifies the beat rate in beats per minute.
        sequencer.setTempoInBPM(220)
        // Sequencer starts to play notes
        sequencer.start()
      }
    })
  }
}
