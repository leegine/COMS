head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�֊m�F���X�|���X(WEB3FXTransFromFXConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��537
Revesion History : 2009/06/25 �đo�g (���u) ���f��No.1166
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX����U�֊m�F���X�|���X) <BR>
 * FX����U�֊m�F���X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_confirm";

    /**
     * (��n��) <BR>
     * ��n�� <BR>
     */
    public Date deliveryDate;

    /**
     * (FX����U�։\�z���)<BR>
     * FX����U�։\�z���<BR>
     */
    public WEB3FXTransferAbleAmtUnit fxTransferAbleAmtUnit;

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * @@roseuid 41E7721303D8
     */
    public WEB3FXTransFromFXConfirmResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransFromFXConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
