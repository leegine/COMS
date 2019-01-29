head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerTryOrderProviderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�T�[�o�g���C�I�[�_�擾����R�[���o�b�N�C���^�t�F�[�X(Web3ServerTryOrderProviderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */

package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * �T�[�o�g���C�I�[�_�擾����R�[���o�b�N�C���^�t�F�[�X
 * **/
public interface WEB3ServerTryOrderProviderCallback
    extends Serializable
{

    /**
     * �Z�b�V����ID��TagName
     */
    final public static String SESSION_ID = "session_id";

    /**
     * �A�J�E���gID��TagName
     */
    final public static String ACCOUNT_ID = "account_id";

    /**
     * ���ʃR�[�h���Q����TagName
     */
    final public static String ORDER_REQUEST_NUMBER = "head2_order_request_number";

    /**
     * �ڋqID�����W��TagName
     */
    final public static String ACCOUNT_ID_RANGE = "account_id_range";

    /**
     * �Ή��ς�TagName
     */
    final public static String[] TAGNAMES =
        {
        SESSION_ID, ACCOUNT_ID, ORDER_REQUEST_NUMBER, ACCOUNT_ID_RANGE};

    /**
     * �����tagName��TagValue�ɑ΂��ăg���C�I�[�_��񋟂���R�[���o�b�N���\�b�h�ł��B
     *
     * @@param tagName tagValue�擾�̂��߂ɗ��p�����Request�d����Field���iXML�d���̏ꍇ�AtagName�j
     * @@param tagValue String
     * @@return ServerAccessor�̃g���C�I�[�_
     */
    public abstract int[] getServerTryOrder(String tagName, String tagValue);

}
@
