using System;
using System.Text.RegularExpressions;

namespace DofusProtocolBuilder.Parsing.Elements
{
	public class ForStatement : IStatement
	{
        public static string Pattern = @"\bfor\s*\(\s*(?<vars>[^;]+)\s*;\s*(?<condition>[^;]+)\s*;\s*(?<increment>[^;]+)\s*\)";

		public string Iterated { get; set; }
		public string Condition { get; set; }

		public string Iterator { get; set; }

        public static IStatement Parse(string str)
        {
            Match match = Regex.Match(str, Pattern, RegexOptions.Multiline);
            ControlStatement result = null;

            if (match.Success)
            {
                result = new ControlStatement();

                result.ControlType = ControlType.For;

                if (match.Groups["condition"].Value != "")
                {
                    result.Condition = match.Groups["condition"].Value;
                }
            }

            return result;
        }
	}
}