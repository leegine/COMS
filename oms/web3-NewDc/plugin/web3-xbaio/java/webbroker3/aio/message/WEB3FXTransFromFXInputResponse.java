head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�֓��̓��X�|���X(WEB3FXTransFromFXInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
Revesion History : 2009/06/25 �đo�g (���u) ���f��No.1166
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX����U�֓��̓��X�|���X) <BR>
 * FX����U�֓��̓��X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX����U�։\�z���ꗗ)<BR>
     * FX����U�։\�z���̈ꗗ<BR>
     */
    public WEB3FXTransferAbleAmtUnit[] fxTransferAbleAmtList;

    /**
     * (�U�֏����) <BR>
     * �ڋq��1���̐U�֏���� <BR>
     */
    public String transferCountUpper;

    /**
     * (�U�։�) <BR>
     * �ڋq�̌����_�ł�1���̐U�։� <BR>
     */
    public String transferCount;

    /**
     * @@roseuid 41E779B20290
     */
    public WEB3FXTransFromFXInputResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransFromFXInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}@
