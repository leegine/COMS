head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֊������X�|���X(WEB3FXTransToFXCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 ���z (���u) �V�K�쐬
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��537
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�ւ̐U�֊������X�|���X) <BR>
 * FX�ւ̐U�֊������X�|���X�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
    
    /**
     * (��n��) <BR>
     * ��n�� <BR>
     */
    public Date deliveryDate;
    
    /**
     * (�X�V����) <BR>
     * DB�̍X�V���� <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (���ʔԍ�) <BR>
     * ����ID <BR>
     */
    public String orderActionId;

    /**
     * @@roseuid 41E76935000F
     */
    public WEB3FXTransToFXCompleteResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransToFXCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
