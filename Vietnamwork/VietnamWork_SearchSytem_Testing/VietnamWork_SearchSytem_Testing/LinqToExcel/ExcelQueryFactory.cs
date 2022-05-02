using System;

namespace LinqToExcel
{
    internal class ExcelQueryFactory
    {
        private string v;

        public ExcelQueryFactory(string v)
        {
            this.v = v;
        }

        internal object Worksheet(string v)
        {
            throw new NotImplementedException();
        }
    }
}