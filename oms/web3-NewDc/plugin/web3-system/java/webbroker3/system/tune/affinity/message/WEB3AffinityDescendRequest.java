head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityDescendRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���菈�����N�G�X�g�iAffinity�j�N���X(WEB3AffinityDescendRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 �� �V�K�쐬
 */
package webbroker3.system.tune.affinity.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * ���菈�����N�G�X�g�iAffinity�j
 */
public class WEB3AffinityDescendRequest
    extends Request
{

    /**
     * �|�������t�B�b�N�^�C�v
     */
    public static final String PTYPE = "descend_request";

    /**
     * �V���A���o�[�W����UID
     */
    public static final long serialVersionUId = 200409081330L;

    /**
     * �ڋqID
     */
    public String account_id;

    /**
     * ���ʃR�[�hID
     */
    public String head2_order_request_number;

    /**
     * �ڋqID�����W
     */
    public String account_id_range;

    /**
     * �T�u���N�G�X�g
     */
    public Request request[];
}
@
