head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W������ꌏ�ʒm�������X�|���X(WEB3RlsCondOrderSubmitResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/15 �� (FLJ)�V�K�쐬
 */

package webbroker3.omsadaptor.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * �i���[���G���W������ꌏ�����ʒm�ɑ΂��郌�X�|���X�j�B<br>
 * <br>
 * ���[���G���W������ꌏ�����ʒm�ɑ΂��郌�X�|���X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderSubmitResponse
    extends Response
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
     * �G���[���B
     */
    public ErrorInfo errorInfo = null;

}
@
