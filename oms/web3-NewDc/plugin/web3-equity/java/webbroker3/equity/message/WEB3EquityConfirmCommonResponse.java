head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������m�F���ʃ��X�|���X(WEB3EquityConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 ���_�� (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i���������m�F���ʃ��X�|���X�j�B<br>
 * <br>
 * ���������m�F���ʉ����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityConfirmCommonResponse extends WEB3GenResponse
{
    /**
     * �m�F��������<BR>
     */
    public Date checkDate;

    /**
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;

    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * <BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;

    /**
     * SerialVersionUID<BR>
     */
    public static  final long serialVersionUID = 20040520001L;

    /**
     * PTYPE<BR>
     */
    public static  final String PTYPE = "equity_common";

    /**
     * @@roseuid 409EFF6B02DC
     */
    public WEB3EquityConfirmCommonResponse()
    {

    }
    public WEB3EquityConfirmCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
