head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXAskingResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�ֈ˗����X�|���X(WEB3FXTransFromFXAskingResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FX����U�ֈ˗����X�|���X) <BR>
 * FX����U�ֈ˗����X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXAskingResponse extends WEB3FXAskingCommonResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_asking";

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
     * @@roseuid 41E78812003E
     */
    public WEB3FXTransFromFXAskingResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransFromFXAskingResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
