package jp.hishidama.eval.rule;

import jp.hishidama.eval.exp.*;
import jp.hishidama.eval.lex.Lex;

/**
 * �񍀉��Z�q�i�������j���[���N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html">�Ђ�����</a>
 */
public class Col2Rule extends AbstractRule {

	public Col2Rule(ShareRuleValue share) {
		super(share);
	}

	@Override
	protected AbstractExpression parse(Lex lex) {
		AbstractExpression x = nextRule.parse(lex);
		while (true) {
			switch (lex.getType()) {
			case Lex.TYPE_OPE:
				String ope = lex.getOperator();
				if (isMyOperator(ope)) {
					int pos = lex.getPos();
					AbstractExpression y = nextRule.parse(lex.next());
					x = Col2Expression.create(
							newExpression(ope, lex.getShare()),
							lex.getString(), pos, x, y);
					continue;
				}
				return x;
			default:
				return x;
			}
		}
	}
}
