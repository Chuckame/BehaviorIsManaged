#region License GNU GPL
// MessageHandlerAttribute.cs
// 
// Copyright (C) 2012 - BehaviorIsManaged
// 
// This program is free software; you can redistribute it and/or modify it 
// under the terms of the GNU General Public License as published by the Free Software Foundation;
// either version 2 of the License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details. 
// You should have received a copy of the GNU General Public License along with this program; 
// if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
#endregion
using System;
using BiM.Core.Network;

namespace BiM.Core.Messages
{
    public class MessageHandlerAttribute : Attribute
    {
        public MessageHandlerAttribute()
        {
            HandleChildMessages = true;
        }

        public MessageHandlerAttribute(Type type)
        {
            MessageType = type;
            HandleChildMessages = true;
        }

        public Type MessageType
        {
            get;
            set;
        }

        public ListenerEntry FromFilter
        {
            get;
            set;
        }

        public ListenerEntry DestinationFilter
        {
            get;
            set;
        }

        public Type FilterType
        {
            get;
            set;
        }

        public bool HandleChildMessages
        {
            get;
            set;
        }
    }
}