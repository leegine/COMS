head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityAccessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :webbroker3�J�X�^�}�C�YAffinityAccessor�N���X(web3AffinityAccessor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import webbroker3.system.tune.affinity.util.*;

/**
 * webbroker3�J�X�^�}�C�YAffinityAccessor
 * **/
public class WEB3AffinityAccessor
    extends AffinityAccessor
{

    /**
     * ���C�I�[�_��񋟂���I�u�W�F�N�g
     */
    private final WEB3ServerTryOrderProviderCallback m_tryOrderProvider;

    /**
     * �R���X�g���N�^
     *
     * @@param tagName tagValue�擾�̂��߂�Request�d����Field���iXML�d���̏ꍇ�AtagName�j
     * @@param urls �N���X�^APP�T�[�o�[��URL���
     * @@param timeoutOnFailureMillis �ڑ����s���ɃT�[�o�ɑ΂��ă_�E���Ɛݒ肷�鎞�Ԃ̒���
     * @@param callback ���C�I�[�_��񋟂���I�u�W�F�N�g
     */
    public WEB3AffinityAccessor(String tagName, String urls[],
                                long timeoutOnFailureMillis,
                                WEB3ServerTryOrderProviderCallback callback)
    {
        super(tagName, urls, timeoutOnFailureMillis);
        this.m_tryOrderProvider = callback;

    }

    /**
     * �R���X�g���N�^�idefault timeoutOnFailureMillis value (10s) �j
     * @@param tagName tagValue�擾�̂��߂�Request�d����Field���iXML�d���̏ꍇ�AtagName�j
     * @@param urls �N���X�^APP�T�[�o�[��URL���
     * @@param callback ���C�I�[�_��񋟂���I�u�W�F�N�g
     */
    public WEB3AffinityAccessor(String tagName, String urls[],
                                WEB3ServerTryOrderProviderCallback callback)
    {
        super(tagName, urls);
        this.m_tryOrderProvider = callback;

    }

    /**
     * �g���C�I�[�_���擾����I�[�o���C�h���ꂽ���\�b�h�ł��B
     *
     * @@param tagValue String
     * @@return �I�[�o���C�h
     */
    protected int[] deriveOrderFromTagValue(String tagValue)
    {
        int tryOrder[] = m_tryOrderProvider.getServerTryOrder(this.getTagName(), tagValue);
        if (tryOrder == null || tryOrder.length == 0)
        {
            ClientLogging.warn(
                "getServerTryOrder returns null or zero-length array for tagName= " +
                this.getTagName() + "& tagValue=" + tagValue +
                ",will use round robin try order for message submission.");

            int[] roundRobinOrder = getRoundRobinOrder();
            ClientLogging.debug(
                "tagValue=" + tagValue + "," +
                " deriveOrderFromTagValue()= " +
                WEB3SystemObjectPrint.print(roundRobinOrder)
                );
            return roundRobinOrder;
        }
        else
        {
            int[] roundRobinOrder = getRoundRobinOrder();
            if (roundRobinOrder.length != tryOrder.length && tryOrder.length == 1)
            {
                int fixedTryOrder[] = new int[roundRobinOrder.length];
                for (int i = 0; i < roundRobinOrder.length; i++)
                {
                    fixedTryOrder[i] = roundRobinOrder[i];
                }
                for (int i = 0; i < roundRobinOrder.length; i++)
                {
                    if (roundRobinOrder[i] == tryOrder[0])
                    {
                        fixedTryOrder[i] = roundRobinOrder[0];
                        fixedTryOrder[0] = tryOrder[0];
                        break;
                    }
                }
                ClientLogging.debug(
                    "tagValue=" + tagValue + "," +
                    " getRoundRobinOrder()= " +
                    WEB3SystemObjectPrint.print(roundRobinOrder)
                    );
                ClientLogging.debug(
                    "tagValue=" + tagValue + "," +
                    " deriveOrderFromTagValue()= " +
                    WEB3SystemObjectPrint.print(fixedTryOrder)
                    );
                return fixedTryOrder;
            }
            else
            {
                ClientLogging.debug(
                    "tagValue=" + tagValue + "," +
                    " deriveOrderFromTagValue()= " +
                    WEB3SystemObjectPrint.print(tryOrder)
                    );
                return tryOrder;
            }
        }
    }

}
@
