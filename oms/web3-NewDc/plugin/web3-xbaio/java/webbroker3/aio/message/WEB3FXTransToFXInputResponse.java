head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֓��̓��X�|���X(WEB3FXTransToFXInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�ւ̐U�֓��̓��X�|���X) <BR>
 * FX�ւ̐U�֓��̓��X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX�������ꗗ) <BR>
     * FX�������̈ꗗ <BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

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
     * (�U�։\�z) <BR>
     * �ڋq�̌����_�ł̐U�։\�z <BR>
     */
    public String transferableAmt;

    /**
     * @@roseuid 41E780B201C5
     */
    public WEB3FXTransToFXInputResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransToFXInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
