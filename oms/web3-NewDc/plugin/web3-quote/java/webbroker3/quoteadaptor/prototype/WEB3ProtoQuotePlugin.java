head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3ProtoQuotePlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �v���g�^�C�v�p�̎����T�[�r�X�v���O�C���N���X(WEB3ProtoQuotePlugin.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/02 �R�c�@@��i(FLJ) �V�K�쐬
 *                  : 2006/08/23 �R�c�@@��i(FLJ) �uWEB3_QUOTE_PROTO�v�e�[�u���̃L���b�V���^�C�v��MASTER��SESSION�ɕύX
 */
package webbroker3.quoteadaptor.prototype;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.prototype.data.WEB3QuoteProtoSessionDatabaseExtensions;
import webbroker3.util.WEB3LogUtility;

/**
 * �v���g�^�C�v�p�̎����T�[�r�X�v���O�C���N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3ProtoQuotePlugin extends Plugin
{
    
    private static final WEB3LogUtility _log = WEB3LogUtility.getInstance(WEB3ProtoQuotePlugin.class);

    /**
     * �R���X�g���N�^ 
     */
    public WEB3ProtoQuotePlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3ProtoQuotePlugin.class);
    }

    public static void onPlug() throws Exception
    {

        // KernelPlugin���C���X�g�[��
        KernelPlugin.plug();

        // �v���^�C�v�p�̎����f�[�^�e�[�u�����C���X�g�[��
        WEB3QuoteProtoSessionDatabaseExtensions.plug();

        // Enum��o�^
        registerEnumerated(AskPriceTitle.class);
        registerEnumerated(BidPriceTitle.class);
        registerEnumerated(CurrentPriceFlag.class);
        registerEnumerated(DataType.class);
        registerEnumerated(IndexType.class);
        registerEnumerated(PutAndCall.class);
        registerEnumerated(RealType.class);

        // �����T�[�r�X�̃C���X�^���X�𐶐�
        WEB3QuoteDataSupplierService supplier =
            new WEB3ProtoQuoteDataSupplierService();

        // �����g���[�f�B���O���W���[���Ɏ����T�[�r�X���C���X�g�[��
        try
        {
            TradingModule tm = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            tm.installQuoteDataSupplierService(supplier);
            _log.info("QuoteDataSupplierService installed to EqType TradingModule.");
        } catch (NotInstalledException nie)
        {
            _log.error("Failed in installing QuoteDataSupplierService to EqType TradingModule.", nie);
        }
        
        // �敨�I�v�V�����g���[�f�B���O���W���[���Ɏ����T�[�r�X���C���X�g�[��
        try
        {
            TradingModule tm = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            tm.installQuoteDataSupplierService(supplier);
            _log.info("QuoteDataSupplierService installed to IFO TradingModule.");
        } catch (NotInstalledException nie)
        {
            _log.error("Failed in installing QuoteDataSupplierService to IFO TradingModule.", nie);
        }
        
    }

    private static void registerEnumerated(Class enumeratedClass)
        throws Exception
    {
        EnumeratedManager.getInstance().addEnumeratedClass(enumeratedClass);
    }

}
@
