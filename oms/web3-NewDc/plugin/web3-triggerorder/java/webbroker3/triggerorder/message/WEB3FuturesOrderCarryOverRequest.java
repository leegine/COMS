head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����J�z���X�|���X�N���X(WEB3FuturesOrderCarryOverRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 䈋� (���u) �V�K�쐬
Revesion History : 2007/06/29 ����  (���u)  �d�l�ύX ���f�� 671

*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���敨�����J�z���X�|���X)<BR>
 * �����w���敨�����J�z���X�|���X�N���X<BR>
 */
public class WEB3FuturesOrderCarryOverRequest extends WEB3IfoOrderCarryOverMainRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201304L;
    
    /**
     * @@roseuid 40F7AE0D02EE
     */
    public WEB3FuturesOrderCarryOverRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * super.validate()���R�[������B <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }
    
    /**
     * @@return WEB3BackResponse
     * @@roseuid 40F7AE0D02FD
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FuturesOrderCarryOverResponse(this);
    }
}
@
