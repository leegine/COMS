head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���|�W�V�����}�l�[�W��(WEB3AioPositionManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ����  (���u) �V�K�쐬
                   2004/10/27 ���z (���u) ���r���[
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioPositionManagerImpl;


/**
 * (���o���|�W�V�����}�l�[�W��)<BR>
 * ���o���|�W�V�����}�l�[�W���N���X<BR>
 * �iAioPositionManager�̊g���N���X�j<BR>
 * 
 * @@author ����(���u)<BR>
 * @@version 1.0 
 */

public class WEB3AioPositionManager extends AioPositionManagerImpl 
{
    
    /**
     * (���o���|�W�V�����}�l�[�W��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * m_tradingType = ProductTypeEnum.AIO<BR>
     * m_helper = new ���o���|�W�V�����w���p�[<BR>
     * <BR>
     * ���Z�b�g����B<BR>
     * <BR>
     * @@roseuid 413EDA6C0317
     */
    public WEB3AioPositionManager() 
    {
        super.m_helper = 
            new WEB3AioPositionManagerHelper(ProductTypeEnum.AIO);
    }
}
@
