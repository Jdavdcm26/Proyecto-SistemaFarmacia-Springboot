async function cargarBadges(userId) {
    try {
        // Badge de productos
        const res = await fetch(`/api/usuarios/${userId}/productos`);
        if (res.ok) {
            const productos = await res.json();

            const bajos = productos.filter(p =>
                p.stockMinimo !== null && p.stock <= p.stockMinimo && p.stock > 0
            );
            const sinStock = productos.filter(p => p.stock === 0);

            const totalAlertas = bajos.length + sinStock.length;
            const badgeProd = document.getElementById('badge-medicamentos');

            if (badgeProd) {
                if (totalAlertas > 0) {
                    badgeProd.textContent = totalAlertas;
                    badgeProd.classList.remove('hidden');
                } else {
                    badgeProd.classList.add('hidden');
                }
            }
        }

        // Badge de categorías
        const catRes = await fetch(`/api/usuarios/${userId}/categorias`);
        if (catRes.ok) {
            const categorias = await catRes.json();
            const badgeCat = document.getElementById('badge-categorias');

            if (badgeCat) {
                if (categorias.length > 0) {
                    badgeCat.textContent = categorias.length;
                    badgeCat.classList.remove('hidden');
                } else {
                    badgeCat.classList.add('hidden');
                }
            }
        }

        // Badge de clientes
        const clientesRes = await fetch(`/api/usuarios/${userId}/clientes`);
        if (clientesRes.ok) {
            const clientes = await clientesRes.json();
            const badgeClientes = document.getElementById('badge-clientes');

            if (badgeClientes) {
                if (clientes.length > 0) {
                    badgeClientes.textContent = clientes.length;
                    badgeClientes.classList.remove('hidden');
                } else {
                    badgeClientes.classList.add('hidden');
                }
            }
        }

        // ✅ Badge de ventas (CORREGIDO)
        const ventasRes = await fetch(`/api/usuarios/${userId}/ventas`);
         if (ventasRes.ok) {
            const ventas = await ventasRes.json();
            const badgeVentas = document.getElementById('badge-ventas');

            if (!badgeVentas) return;

            const activas = ventas.filter(v =>
            v.estado && v.estado.trim().toUpperCase() !== 'ANULADA'
            ).length;

            if (activas > 0) {
            badgeVentas.textContent = activas;
            badgeVentas.classList.remove('hidden');
            } else {
                badgeVentas.classList.add('hidden');
         }
        }
    } catch (error) {
        console.warn('Error cargando badges:', error);
    }
}
/// 🔹 Cargar categorías para formularios (select)
async function cargarCategoriasSelect(userId, selectId = 'categoria') {
    try {
        const res = await fetch(`/api/usuarios/${userId}/categorias`);

        if (!res.ok) {
            console.warn('No se pudieron cargar categorías');
            return [];
        }

        const categorias = await res.json();

        const select = document.getElementById(selectId);
        if (!select) return categorias;

        // 🔥 Limpiar opciones previas
        select.innerHTML = `<option value="">Seleccione una categoría</option>`;

        // 🔥 Llenar select
        categorias.forEach(cat => {
            const option = document.createElement('option');
            option.value = cat.id;
            option.textContent = cat.nombre;
            select.appendChild(option);
        });

        return categorias;

    } catch (error) {
        console.error('Error cargando categorías:', error);
        return [];
    }
}