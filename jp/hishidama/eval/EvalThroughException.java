package jp.hishidama.eval;

/**
 * ���Z�G���[�i�X���[�j�N���X.
 * <p>
 * ���Z�̌��ʐ��������s����O�����̂܂ܕԂ��ׂ̗�O�B<br>
 * {@link Expression#eval()}����Ă΂ꂽ�����̒��œ���O���X���[����ƁA
 * eval()����͓���O�Ń��b�v���ꂽ���s����O���X���[����B
 * </p>
 *
 * @see Expression#eval()
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
@SuppressWarnings("serial")
public class EvalThroughException extends EvalException {

	public EvalThroughException(RuntimeException e) {
		super(e);
	}

	@Override
	public String toString() {
		return getCause().toString();
	}
}
