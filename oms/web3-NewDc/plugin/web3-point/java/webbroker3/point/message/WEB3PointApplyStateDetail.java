head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyStateDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\���󋵖���(WEB3PointApplyStateDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�\���󋵖���)<BR>
 * �\���󋵖��׃N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyStateDetail extends Message
{
    
    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public Date applyDate;
    
    /**
     * (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     */
    public String premiumNo;
    
    /**
     * (�i�i��)<BR>
     * �i�i��<BR>
     */
    public String premiumName;
    
    /**
     * (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     */
    public String requiredPoint;
    
    /**
     * (���l)<BR>
     * ��t�敪�Ǝ���敪���画�肵���\���̏�<BR>
     * <BR>
     * 0�F ��t��<BR>
     * 1�F ��t��<BR>
     * 2�F ���<BR>
     * <BR>
     */
    public String applyStateDiv;
    
    /**
     * (���͋敪)<BR>
     * ���͋敪�i�\���o�H�敪�j<BR>
     * <BR>
     * 1�F �R�[���Z���^�[<BR>
     * 2�F �o�b<BR>
     * 3�F �X�����O�V���b�g<BR>
     * 4�F i-mode<BR>
     * 5�F Vodafone<BR>
     * 6�F AU<BR>
     * 9�F HOST<BR>
     * A�F �Ǘ���<BR>
     * <BR>
     */
    public String orderRootDiv;
    
    /**
     * (����)<BR>
     * �\�����s�����I�y���[�^�[�̃I�y���[�^��R�[�h<BR>
     */
    public String operatorCode;
    
    /**
     * (�\���󋵖���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 418F55E90240
     */
    public WEB3PointApplyStateDetail() 
    {
     
    }
}
@
