head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginAttentionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍώ����ӕ���(WEB3MarginCloseMarginAttentionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 �򑺐m�m (SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p����ԍώ����ӕ����j�B<BR>
 * <BR>
 * �M�p����ԍώ����ӕ����N���X<BR>
 */
public class WEB3MarginCloseMarginAttentionUnit extends Message 
{
    /**
     * (���ӕ����\���敪)<BR>
     * <BR>
     * ���ӕ����\���敪�B <BR>
     * �i0�F���ӕ����\���Ȃ� <BR>
     * �@@1�F���ӕ����\���L��(�ԍϑO������������) <BR>
     * �@@2�F���ӕ����\���L��(�ԍϑO���������L��)�j<BR>
     */
    public String attentionDispDiv;
    
    /**
     * (���������z)<BR>
     * <BR>
     * ���������z�B<BR>
     */
    public String payClaimAmount;
    
    /**
     * (�M�p����ԍώ����ӕ���)<BR>
     * <BR>
     * �R���X�g���N�^�B
     * @@return WEB3MarginCloseMarginAttentionUnit
     */
    public WEB3MarginCloseMarginAttentionUnit() 
    {
     
    }
}
@
