head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointPremiumUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i����(WEB3PointPremiumUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�i�i����)<BR>
 * �i�i���׃N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointPremiumUnit extends Message
{
    
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
     * (�i�i����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 418F5871034B
     */
    public WEB3PointPremiumUnit() 
    {
     
    }
}
@
