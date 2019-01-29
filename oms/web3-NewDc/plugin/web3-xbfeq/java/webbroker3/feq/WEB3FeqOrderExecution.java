head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderExecution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������(WEB3FeqOrderExecution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderExecutionImpl;


/**
 * (�O���������)<BR>
 * �O���������<BR>
 * 
 * @@ author ���� 
 * @@ version 1.0 
 */
public class WEB3FeqOrderExecution extends FeqOrderExecutionImpl 
{
    /**
     * �R���X�g���N�^<BR>
     * 
     */
    public WEB3FeqOrderExecution(long order_execution_id)
        throws DataQueryException, DataNetworkException
    {
        super(order_execution_id);
    }
    
    /**
     * �R���X�g���N�^<BR>
     * 
     */
    public WEB3FeqOrderExecution(FeqOrderExecutionRow row)
    {
       super(row);
    }

    /**
     * (getOrderId)<BR>
     * �igetOrderId�j<BR>
     * �����h�c���擾����B<BR>
     * <BR>
     * this.���s.�����h�c��ԋp����B<BR>
     * @@return long
     * @@roseuid 4295CBBE01AC
     */
    public long getOrderId() 
    {
        return this.m_row.getOrderId();
    }
    
    /**
     * (is�폜��)<BR>
     * �폜�ς��𔻒肷��B<BR>
     * <BR>
     * this.���s.�폜�t���O��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 42BF9F5F0203
     */
    public boolean isDeleted() 
    {
        return BooleanEnum.TRUE.equals(this.m_row.getDeleteFlag()) ? true : false;
    }
}
@
