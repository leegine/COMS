head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAsset.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۗL���Y�N���X(WEB3EquityAsset.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 �����@@���F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAssetUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

/**
 * �i�ۗL���Y�j�B<BR>
 * <BR>
 * ���x�[�X�̎c����\������B<BR>
 * �擾�P�ʂɊ֌W�Ȃ��A�O���X�ŕێ�����B<BR>
 * xTrade��EqTypeAsset���g�������N���X�B
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3EquityAsset extends EqTypeAssetImpl
{

    /**
     * (�R���X�g���N�^�B)<BR>
     *<BR> 
     * @@param l_assetId ���YID
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3EquityAsset(long l_lngAssetId) throws DataQueryException, DataNetworkException
    {
        super(l_lngAssetId);
    }

    /**
     * (�R���X�g���N�^�B)<BR>
     *<BR> 
     * @@param l_row EqtypeAssetRow�I�u�W�F�N�g
     */
    public WEB3EquityAsset(AssetRow l_row)
    {
        super(l_row);
    }

    protected EqTypeAssetUnit toAssetUnit(Row l_row)
    {
        return null;
    }
}
@
