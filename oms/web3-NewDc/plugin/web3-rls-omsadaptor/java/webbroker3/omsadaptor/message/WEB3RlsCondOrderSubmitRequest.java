head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W������ꌏ�����ʒm���N�G�X�g(WEB3RlsCondOrderSubmitRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/15 �� (FLJ)�V�K�쐬
 */

package webbroker3.omsadaptor.message;

import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * �i���[���G���W������ꌏ�����ʒm���N�G�X�g�j�B<br>
 * <br>
 * ���[���G���W������ꌏ�����ʒm���N�G�X�g
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderSubmitRequest
    extends Request
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "rls_cond_order_submit";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509150000L;

    /**
     * ����ID<BR>
     */
    public long account_id;

    /**
     * �⏕����ID<BR>
     */
    public long sub_account_id;

    /**
     * �����t�����^�C�v<BR>
     */
    public int order_type;

    /**
     * �����t����ID<BR>
     */
    public long con_order_id;

    /**
     * �����t�����̏��i�^�C�v<BR>
     */
    public ProductTypeEnum product_type;

}
@
