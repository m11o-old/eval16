package jp.hishidama.eval.log;

/**
 * ���Z�����O�o�̓A�_�v�^�[�N���X.
 * <p>
 * {@link EvalLog ���O�o�̓C���^�[�t�F�[�X}�����������N���X�B<br>
 * ���N���X�ł́A�����͉����s��Ȃ��B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public class EvalLogAdapter implements EvalLog {

	@Override
	public void logEval(String name, Object r) {
	}

	@Override
	public void logEval(String name, Object x, Object r) {
	}

	@Override
	public void logEval(String name, Object x, Object y, Object r) {
	}

	@Override
	public void logEvalFunction(String name, String funcName, Object[] args,
			Object r) {
	}
}
