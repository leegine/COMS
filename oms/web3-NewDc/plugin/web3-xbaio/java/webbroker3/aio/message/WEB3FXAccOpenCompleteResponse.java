head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�݊������X�|���X(WEB3FXAccOpenCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�����J�݊������X�|���X) <BR>
 * FX�����J�݊������X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (�ב֕ۏ؋����O�C��ID) <BR>
     * �ב֕ۏ؋�����p���O�C��ID <BR>
     */
    public String fxLoginId;

    /**
     * (�ב֕ۏ؋��������ꗗ) <BR>
     * �ב֕ۏ؋��������̈ꗗ <BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * @@roseuid 41E7829901F4
     */
    public WEB3FXAccOpenCompleteResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXAccOpenCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
