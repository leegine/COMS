head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointHistoryDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g���𖾍�(WEB3AdminPointHistoryDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�|�C���g���𖾍�)<BR>
 * �|�C���g���𖾍׃N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointHistoryDetail extends Message
{
    
    /**
     * (�N��)<BR>
     * �N��<BR>
     */
    public String period;
    
    /**
     * (�����|�C���g)<BR>
     * �����|�C���g<BR>
     */
    public String accrualPoint;
    
    /**
     * (���p�|�C���g)<BR>
     * ���p�|�C���g<BR>
     */
    public String usedPoint;
    
    /**
     * (�����|�C���g)<BR>
     * �����|�C���g<BR>
     */
    public String adjustPoint;
    
    /**
     * (�|�C���g���𖾍�)<BR>
     * �R���X�g���N�^ <BR>
     * @@roseuid 41D1283B0261
     */
    public WEB3AdminPointHistoryDetail() 
    {
     
    }
}
@
