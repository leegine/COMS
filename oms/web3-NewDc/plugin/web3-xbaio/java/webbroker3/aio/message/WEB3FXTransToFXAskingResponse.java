head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXAskingResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�ֈ˗����X�|���X(WEB3FXTransToFXAskingResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FX�ւ̐U�ֈ˗����X�|���X) <BR>
 * FX�ւ̐U�ֈ˗����X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXAskingResponse extends WEB3FXAskingCommonResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_asking";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (����ID) <BR>
     * ����ID <BR>
     */
    public String orderId;

    /**
     * (�m�F��������) <BR>
     * �m�F�������̔����� <BR>
     */
    public Date checkDate;

    /**
     * @@roseuid 41E769350222
     */
    public WEB3FXTransToFXAskingResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransToFXAskingResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
