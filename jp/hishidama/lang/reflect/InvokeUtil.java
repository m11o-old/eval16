package jp.hishidama.lang.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.hishidama.lang.IllegalArgumentLengthException;
import jp.hishidama.lang.reflect.conv.TypeConverter;
import jp.hishidama.lang.reflect.conv.TypeConverterManager;

/**
 * ���\�b�h�Ăяo�����[�e�B���e�B�[.
 * <p>
 * �w�肳�ꂽ���O�̃��\�b�h���Ăяo�����[�e�B���e�B�[�B
 * </p>
 * <p>
 * �܂��A�Ăяo�������N���X�̃��\�b�h���Ǘ��p�̖��O��t���ēo�^����B<br>
 * �����āA���̊Ǘ��p�̖��O�Ń��\�b�h���Ăяo���B<br>
 * ���̂Ƃ��A�I�u�W�F�N�g������̌^�͌Ăяo�����\�b�h�ɍ��v����^�ɕϊ�����B
 * </p>
 * <p>
 * �I�[�o�[���[�h����Ă��郁�\�b�h�̏ꍇ�A�����̌�����v���Ă��郁�\�b�h��1��������΁A������Ăяo���B<br>
 * ��������ꍇ�A�Ȃ�ׂ��^����v���Ă��郁�\�b�h���Ăяo���i���Ȃ�K���Ƀ}�b�`�����Ă���̂ŁA����ȂɌ����ł͂Ȃ��c�j�B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class InvokeUtil {

	protected TypeConverterManager manager;

	protected Map<String, Methods> MAP = new HashMap<String, Methods>();

	/**
	 * �R���X�g���N�^�[.
	 */
	public InvokeUtil() {
		this(new TypeConverterManager());
	}

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param manager
	 *            �^�ϊ��I�u�W�F�N�g�Ǘ��N���X
	 */
	public InvokeUtil(TypeConverterManager manager) {
		this.manager = manager;
	}

	/**
	 * �^�ϊ��I�u�W�F�N�g�Ǘ��N���X�擾.
	 *
	 * @return �^�ϊ��I�u�W�F�N�g�Ǘ��N���X
	 */
	public TypeConverterManager getConverterManager() {
		return manager;
	}

	/**
	 * ���\�b�h�o�^.
	 * <p>
	 * �w�肳�ꂽ�N���X�̑S���\�b�h���Ǘ��Ώۂɒǉ�����B<br>
	 * �Ǘ��p�̖��O�ɂ́A���\�b�h���̑O�Ɏw�肳�ꂽ�ړ�����t������B
	 * </p>
	 *
	 * @param clazz
	 *            �N���X
	 * @param prefix
	 *            ���O�̐ړ���
	 * @see #addMethod(Class, String, Method)
	 */
	public void addMethods(Class<?> clazz, String prefix) {
		for (Method m : clazz.getMethods()) {
			addMethod(clazz, prefix + m.getName(), m);
		}
	}

	/**
	 * ���\�b�h�o�^.
	 *
	 * @param clazz
	 *            �ΏۃN���X
	 * @param name
	 *            �Ǘ��p�̖��O
	 * @param method
	 *            ���\�b�h
	 * @see #addInvoker(String, Invoker)
	 */
	public void addMethod(Class<?> clazz, String name, Method method) {
		addInvoker(name, new Invoker(name, clazz, method, manager));
	}

	/**
	 * ���\�b�h�Ăяo���N���X�o�^.
	 *
	 * @param name
	 *            �Ǘ��p�̖��O
	 * @param invoker
	 *            ���\�b�h�Ăяo���N���X
	 */
	public void addInvoker(String name, Invoker invoker) {
		Methods mm = MAP.get(name);
		if (mm == null) {
			mm = createMethods(name);
			MAP.put(name, mm);
		}
		mm.add(invoker);
	}

	protected Methods createMethods(String name) {
		return new Methods();
	}

	/**
	 * ���\�b�h�Ăяo��.
	 *
	 * @param name
	 *            �Ǘ��p�̖��O
	 * @param obj
	 *            �ΏۃI�u�W�F�N�g
	 * @param args
	 *            ����
	 * @return ���\�b�h�̖߂�l
	 * @throws UnsupportedOperationException
	 *             �Ǘ��ΏۊO�̖��O�̏ꍇ
	 * @throws RuntimeException
	 *             ���\�b�h�Ăяo�����ɔ���������O
	 */
	public Object invoke(String name, Object obj, Object... args) {
		Methods mm = MAP.get(name);
		if (mm == null) {
			throw new UnsupportedOperationException("name=" + name);
		}
		try {
			Invoker v = mm.getInvoker(args, BOTH, false);
			return v.invoke(obj, args);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �C���X�^���X���\�b�h�Ăяo���N���X�擾.
	 *
	 * @param name
	 *            �Ǘ��p�̖��O
	 * @param args
	 *            ����
	 * @return ���\�b�h�Ăяo���N���X�i������Ȃ��ꍇ�Anull�j
	 * @throws UnsupportedOperationException
	 *             �Ǘ��ΏۊO�̖��O�̏ꍇ
	 */
	public Invoker getInstanceInvoker(String name, Object... args) {
		Methods mm = MAP.get(name);
		if (mm == null) {
			throw new UnsupportedOperationException("name=" + name);
		}
		return mm.getInvoker(args, DYNAMIC, true);
	}

	/**
	 * �N���X���\�b�h�Ăяo���N���X�擾.
	 *
	 * @param name
	 *            �Ǘ��p�̖��O
	 * @param args
	 *            ����
	 * @return ���\�b�h�Ăяo���N���X�i������Ȃ��ꍇ�Anull�j
	 * @throws UnsupportedOperationException
	 *             �Ǘ��ΏۊO�̖��O�̏ꍇ
	 */
	public Invoker getStaticInvoker(String name, Object... args) {
		Methods mm = MAP.get(name);
		if (mm == null) {
			throw new UnsupportedOperationException("name=" + name);
		}
		return mm.getInvoker(args, STATIC, true);
	}

	protected static final int BOTH = 0;
	protected static final int DYNAMIC = 1;
	protected static final int STATIC = 2;

	/**
	 * ���\�b�h���Ǘ�����N���X�B<br>
	 * ����̖��O�̃��\�b�h�A���Ȃ킿�I�[�o�[���[�h���ꂽ���\�b�h���Ǘ�����B
	 */
	protected static class Methods {

		protected List<Invoker> list = new ArrayList<Invoker>(4);

		public void add(Invoker invoker) {
			list.add(invoker);
			Collections.sort(list, COMP);
		}

		protected static final Comparator<Invoker> COMP = new Comparator<Invoker>() {
			@Override
			public int compare(Invoker o1, Invoker o2) {
				// �����̌������Ȃ�����D��
				return o1.getTypeConverter().length
						- o2.getTypeConverter().length;
			}
		};

		public Invoker getInvoker(Object[] args, int ds, boolean search) {
			Invoker v = resolve(args, ds, search);
			return v;
		}

		protected static class Index {
			public int min, max;

			public Index(int min, int max) {
				this.min = min;
				this.max = max;
			}
		}

		protected Invoker resolve(Object[] args, int ds, boolean search) {
			// �����̌�����v���Ă�����̂�T��
			Index index = getInvokerEqualsLength(args, ds);
			if (index.min == index.max) {
				// 1������������
				return list.get(index.min);
			}
			if (index.min < index.max) {
				// ������₠��
				Invoker r = getInvokerMatchType(index, args, ds);
				if (r == null) {
					r = list.get(index.min); // ������Ȃ�������擪�̂��̂��g�p
				}
				return r;
			}

			// TODO+++args.length���傫���Ƃ��F�ϒ������Ƃ�

			if (search) {
				return null;
			}

			int min = list.get(0).getTypeConverter().length;
			int max = list.get(list.size() - 1).getTypeConverter().length;
			throw new IllegalArgumentLengthException(args.length, min, max);
		}

		protected Index getInvokerEqualsLength(Object[] args, int ds) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < list.size(); i++) {
				Invoker v = list.get(i);
				switch (ds) {
				case DYNAMIC:
					if (v.isStatic()) {
						continue;
					}
					break;
				case STATIC:
					if (!v.isStatic()) {
						continue;
					}
					break;
				}
				if (v.getTypeConverter().length == args.length) {
					min = Math.min(min, i);
					max = Math.max(max, i);
				}
			}
			return new Index(min, max);
		}

		protected Invoker getInvokerMatchType(Index index, Object[] args, int ds) {
			int max = Integer.MIN_VALUE;
			Invoker ret = null;
			for (int i = index.min; i <= index.max; i++) {
				Invoker v = list.get(i);
				switch (ds) {
				case DYNAMIC:
					if (v.isStatic()) {
						continue;
					}
					break;
				case STATIC:
					if (!v.isStatic()) {
						continue;
					}
					break;
				}
				TypeConverter[] convs = v.getTypeConverter();
				int cc = 0;
				for (int j = 0; j < args.length; j++) {
					int c = convs[j].match(args[j]);
					if (c < 0) {
						cc = -1;
						break;
					}
					cc += c;
				}
				if (cc >= 0) {
					if (max < cc) {
						max = cc;
						ret = v;
					}
				}
			}
			return ret;
		}
	}
}
