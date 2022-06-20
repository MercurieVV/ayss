package com.github.mercurievv
package ayss.model
import javax.sound.midi.ShortMessage

enum MidiCommand[Channel, Data1 <: Data, Data2 <: Data](value: Int, data1: Data1, data2: Data2):
  case NoteOn(data1: Data.Note, data2: Data.Velocity)  extends MidiCommand(ShortMessage.NOTE_ON, data1, data2)
  case NoteOff(data1: Data.Note, data2: Data.Velocity) extends MidiCommand(ShortMessage.NOTE_OFF, data1, data2)
  case PolyPressure(data1: Data.Note, data2: Data.Pressure)
      extends MidiCommand(ShortMessage.POLY_PRESSURE, data1, data2)
  case ControlChange(data1: Data.ControllerNumber, data2: Data.ControllerValue)
      extends MidiCommand(ShortMessage.CONTROL_CHANGE, data1, data2)

  case ProgramChange(data1: Data.ProgramNumber, data2: Data.ProgramNumber)
      extends MidiCommand(ShortMessage.PROGRAM_CHANGE, data1, data2)

  case ChannelPressure(data1: Data.ChannelPressure, data2: Data.ChannelPressure)
      extends MidiCommand(ShortMessage.CHANNEL_PRESSURE, data1, data2)

  case PitchBend(data1: Data.PitchBendValue, data2: Data.PitchBendValue)
      extends MidiCommand(ShortMessage.PITCH_BEND, data1, data2)

enum Data(value: Int):
  case Note(value: Int)             extends Data(value)
  case Velocity(value: Int)         extends Data(value)
  case Pressure(value: Int)         extends Data(value)
  case ControllerNumber(value: Int) extends Data(value) // 0-119
  case ControllerValue(value: Int)  extends Data(value) // 0-127
  case ProgramNumber(value: Int)    extends Data(value)
  case ChannelPressure(value: Int)  extends Data(value)
  case PitchBendValue(value: Int)   extends Data(value) // 2 values = least significant 7 bits, the significant 7 bits
